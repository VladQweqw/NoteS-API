from django.db import models
import datetime
from django.contrib.auth.models import AbstractBaseUser, BaseUserManager, PermissionsMixin

class CustomAccountManager(BaseUserManager): 
    def create_superuser(self, nickname, password, **other_fields):
        other_fields.setdefault('is_superuser', True)
        other_fields.setdefault('is_staff', True)
        other_fields.setdefault('is_active', True)
        
        if other_fields.get('is_superuser') is not True:
            raise ValueError("Super user must be assigned to is_superuser=True")
        
        if other_fields.get('is_staff') is not True:
            raise ValueError("Super user must be assigned to is_staff=True")
        
        return self.create_user(nickname=nickname, password=password, **other_fields)
        
    def create_user(self, nickname, password, **other_fields):
        other_fields.setdefault('is_superuser', False)
        other_fields.setdefault('is_staff', False)
        other_fields.setdefault('is_active', True)
        
        user = self.model(nickname = nickname, **other_fields)
        user.set_password(password)
        user.save()
        
        return user
        

class NewUser(AbstractBaseUser, PermissionsMixin):
    nickname = models.CharField(max_length = 100, unique = True, default=f"User_{datetime.date.today}")
    creation_date = models.DateTimeField(default=datetime.date.today)
    is_superuser = models.BooleanField(default = False)
    is_staff = models.BooleanField(default = False)
    is_active = models.BooleanField(default = False)
    objects = CustomAccountManager()
    
    USERNAME_FIELD = "nickname"
    REQUIRED_FIELDS = []
    
    def __str__(self):
        return self.nickname
    