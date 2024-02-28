from django.contrib import admin
from django.urls import path
from users import views

urlpatterns = [
    path('login/', views.login_user),
    path('signup/', views.register_user),
    path('logout/', views.logout_user),
    path('update/<int:pk>', views.edit_user),
    path('delete/<int:pk>', views.delete_user),
    
    path('user/<int:pk>', views.get_user_data),
]