import { Component, EventEmitter, Output } from '@angular/core';
import { TransactionService } from '../transaction.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {
  selectedFile?: File;
  @Output() uploadSuccess = new EventEmitter<void>();

  constructor(private transactionService: TransactionService) {}

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
    console.log('Selected file:', this.selectedFile);
  }

  onUpload(): void {
    if (this.selectedFile) {
      this.transactionService.uploadFile(this.selectedFile).subscribe(
        () => {
          alert('File uploaded successfully');
          this.uploadSuccess.emit();
        },
        error => {
          console.error('File upload failed:', error);
          alert(`File upload failed: ${error.error}`);
        }
      );
    }
  }
}