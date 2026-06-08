
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
  comments: comentarios[];
  shares: number;
  likeUsers:CurtidaUsers[]
}

export interface salveComentario {
  publicacao_id: number;
  conteudo: string;
  dataComenatario: Date;
}
export interface comentarios {
  id: number;
  publicacao_id: number;
  usuario: usuario;
  dataComentario: Date;
  conteudo: string;
}
export interface usuario{
  id : number
  nome:string
}
export interface CurtidaUsers{
  id:number
  usuario:number
}

export interface Publicacao {
  publicacao_id: number;
  usuario: usuario;
  conteudo: string;
  likes: CurtidaUsers[];
  comentarios: comentarios[];
  dataPublicacao: string;
}
