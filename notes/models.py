from django.db import models
from django.conf import settings
import datetime
import time

class Note(models.Model):
    title = models.CharField(max_length = 100, default=f"Note_{datetime.date.today}")
    content = models.TextField(blank=True)
    creationDate = models.DateTimeField(auto_now_add=True)
    lastUpdate = models.IntegerField(blank=True, null=True, default = time.time() * 1000.0)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete = models.CASCADE, null=True, blank=True)
    
    def __str__(self):
        return self.title
    
    