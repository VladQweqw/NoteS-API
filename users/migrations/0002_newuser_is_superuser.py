# Generated by Django 5.0.2 on 2024-02-22 15:19

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('users', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='newuser',
            name='is_superuser',
            field=models.BooleanField(default=False),
        ),
    ]
