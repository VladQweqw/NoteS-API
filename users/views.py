from rest_framework.decorators import api_view
from django.http import JsonResponse
from django.contrib.auth import authenticate, login, logout
from django.middleware.csrf import get_token
from django.contrib.auth.hashers import make_password
from users.models import NewUser
from users.serializers import UserSerializer
from rest_framework.response import Response
from rest_framework import status

# Create your views here.

@api_view(['POST'])
def login_user(request):
    csrf_token = get_token(request)
    
    # get the data from the API request
    json_data = request.data
    
    # get the attributes nickname and password
    nickname = json_data.get('nickname')
    password = json_data.get('password')

    user = authenticate(nickname=nickname, password=password)
    if user is not None:
        login(request, user)
        
        # logout(request)
        return JsonResponse({"detail": "Login success!", "csrf_token": csrf_token, "user_id": user.id})
    else:
        return JsonResponse({"error": "Nickname or Password are incorrect!"})
    
@api_view(['POST'])
def logout_user(request):
    logout(request)
    
    return JsonResponse({"response": "User logged out!"})

@api_view(['POST'])
def register_user(request):
    # get the data from the API request
    json_data = request.data
    
    # get the attributes nickname and password
    nickname = json_data.get('nickname')
    password = json_data.get('password')
    
    try:
        NewUser.objects.get(nickname = nickname)
        return JsonResponse({"error": "Nickname is taken"})
    except NewUser.DoesNotExist:
        user = NewUser.objects.create_user(nickname = nickname, password = password)
        
        user.save()
        return JsonResponse({"detail": "User created succesfully!"})
    
@api_view(["POST"])
def get_user_data(request, pk):    
    try:
        user = NewUser.objects.get(id = pk)    
        serializer = UserSerializer(user)
        print(serializer.data)
        return Response({"detail": serializer.data}, status=status.HTTP_200_OK)       
    except NewUser.DoesNotExist:
        return Response({"error": 'Invalid user id'}, status=status.HTTP_404_NOT_FOUND)       
    
@api_view(['PUT'])
def edit_user(request, pk):
    # get the data from the API request
    json_data = request.data
    
    # get the attributes nickname and password
    nickname = json_data.get('nickname')
    new_password = json_data.get('password')
    current_user = NewUser.objects.get(id = pk)
    
    if len(nickname) < 3:
        return JsonResponse({"detail": "Nickname has to be at least 3 letters length"})
    else:
        current_user.nickname = nickname
        
    if len(new_password) < 8:
        return JsonResponse({"detail": "Password too weak"})
    else:
        hashed_password = make_password(new_password)
        current_user.password = hashed_password
        
    
    current_user.save()    
    return JsonResponse({"detail": "User updated"})

@api_view(["DELETE"])
def delete_user(request, pk):
    try:
        user = NewUser.objects.get(id = pk)
        user.delete()
        
        return JsonResponse({"detail": "User deleted succesfully!"})
    
    except NewUser.DoesNotExist:
        return JsonResponse({"detail": "User doesn't not exists"})
                
        