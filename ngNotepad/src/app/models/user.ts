export class User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  username: string;
  password: string;
  createdAt: Date;
  updatedAt: Date;
  role: string;
  enabled: boolean;

  constructor(
    id?: number,
    firstName?: string,
    lastName?: string,
    email?: string,
    username?: string,
    password?: string,
    createdAt?: Date,
    updatedAt?: Date,
    role?: string,
    enabled?: boolean,
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.username = username;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.role = role;
    this.enabled = enabled;
    this.password = password;
  }

}
