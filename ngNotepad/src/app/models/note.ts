import { User } from './user';

export class Note {
  id: number;
  title: string;
  text: string;
  starred: string;
  createdAt: Date;
  updatedAt: Date;
  user: User;

  constructor(
    id?: number,
    title?: string,
    createdAt?: Date,
    updatedAt?: Date,
  ) {
    this.id = id;
    this.title = title;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
