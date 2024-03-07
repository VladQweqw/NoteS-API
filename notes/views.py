from rest_framework.decorators import api_view
from rest_framework.response import Response
from .serializers import NoteSerializer
from django.http import JsonResponse
from rest_framework import status
from .models import Note
from users.models import NewUser

@api_view(['GET', "POST"])
def notes_list(request):
    userId = request.data.get('user') 
    
    try:
        user = NewUser.objects.get(id = userId)
        notes = Note.objects.filter(user = user)
        serializer = NoteSerializer(notes, many=True)
        
        return Response(serializer.data, status=status.HTTP_200_OK)
    except NewUser.DoesNotExist:
        return Response({"error":"User does not exists"}, status=status.HTTP_400_BAD_REQUEST)
    except Exception as e:
        return Response({"error": str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)

@api_view(['POST'])
def individual_note(request, pk): 
    userId = request.data.get('user') 
    
    try:
        user = NewUser.objects.get(id = userId)
        notes = Note.objects.filter(user = user)
        
        if notes is None:
            return Response({"error": "User id or note id are invalid"}, status=status.HTTP_400_BAD_REQUEST)
        else:
            try:
                note = notes.get(id = pk)
                
                serializer = NoteSerializer(note)
                return Response(serializer.data, status=status.HTTP_200_OK)
            except:
                return Response({"error":"Invalid id"}, status=status.HTTP_404_NOT_FOUND)
        
    except NewUser.DoesNotExist:
        return Response({"error":"User does not exists"}, status=status.HTTP_400_BAD_REQUEST)
    except Exception as e:
        return Response({"error": str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)

@api_view(['POST'])
def notes_add(request):
    userId = request.data.get('user')
    
    serializer = NoteSerializer(data = request.data)
    user = NewUser.objects.get(id = userId)
    print(user, userId)
    
    if user is not None:    
        if serializer.is_valid():
            serializer.save()
            return JsonResponse({"detail": "Note added!"})
        else:
            return JsonResponse({"error": "Error while adding the Note"})
    else:
        return JsonResponse({"error": "User doesn't exists"})
        
@api_view(['PUT'])
def notes_update(request, pk):
    data = request.data
    
    userId = data.get('user')
    title = data.get('title')
    content = data.get('content')
    
    try:
        current_user = NewUser.objects.get(id = userId)
        
        notes = Note.objects.filter(user = current_user)
        to_edit_note = notes.get(id = pk)

        
        to_edit_note.title = title
        to_edit_note.content = content
        
        to_edit_note.save()
        
        return JsonResponse({"detail": "Note updated"})
    except NewUser.DoesNotExist:
        return JsonResponse({"error": "User doesn't exists"})
    except Exception as e:
        return JsonResponse({"error": str(e)})
        
@api_view(['DELETE'])
def notes_delete(request, pk):
    userId = request.data.get('user')
    
    try:
        user = NewUser.objects.get(id = userId)
        notes = Note.objects.filter(user = user)
        note = notes.filter(id = pk)
        
        note.delete()    
        return JsonResponse({"detail": "Note deleted succesfully!"})
    
    except NewUser.DoesNotExist:
        return JsonResponse({"detail": "User doesn't not exists"})