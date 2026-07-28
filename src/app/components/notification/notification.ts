import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NotificationService } from '../../services/notification';

@Component({
  selector: 'app-notification',
  standalone: true,
  imports: [CommonModule],
  providers: [NotificationService], // Creates a separate NotificationService instance for this component and its children.
  templateUrl: './notification.html',
  styleUrl: './notification.css'
})
export class Notification {

  constructor(public notificationService: NotificationService) {}

  addNotification(): void {
    this.notificationService.addNotification('New notification received!');
  }

  clearNotifications(): void {
    this.notificationService.clearNotifications();
  }

}