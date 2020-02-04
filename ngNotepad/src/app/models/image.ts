import { User } from './user';

export class Image {
  id: number;
  urlLink: string;
  createdAt: Date;
  updatedAt: Date;
  user: User;

  constructor(
    id?: number,
    urlLink?: string,
    createdAt?: Date,
    updatedAt?: Date,
  ) {
    this.id = id;
    this.urlLink = urlLink;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
