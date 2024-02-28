from django.contrib import admin
from .models import NewUser
from django.contrib.auth.admin import UserAdmin

class UserAdminConfig(UserAdmin):
    search_fields = ('nickname',)
    ordering = ('-creation_date',)
    list_display = ('nickname', 'creation_date', 'is_staff')

    fieldsets = (
        ('Credentials', {'fields': ('nickname',)}),
        ('Permissions', {'fields': ('is_staff', 'is_active',)}),
    )    
    
admin.site.register(NewUser, UserAdminConfig)
