export interface Author {
  id: number;
  name: string;
  position: string;
  avatar: string;
}

export interface Post {
  id: number;
  author: Author;
  content: string;
  date: string;
  image?: string;
  likes: number;
  comments: number;
  shares: number;
}
