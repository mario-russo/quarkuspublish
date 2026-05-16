import type { comentarios } from 'src/components/feed/types';
import api from './api/axiosDomain';

export interface PublishUser {
  publicacao_id: number;
  conteudo: string;
  dataPublicacao: Date;
  comentarios: comentarios[];
  likes: [];
  usuario: { id: number; nome: string };
}
export const publishUser = async (id: number) => {
  const responsse = await api.get<PublishUser[]>('/publicacao/user/' + id);

  return responsse.data;
};
export const publishGetById = async (id: number) => {
  const responsse = await api.get<PublishUser>('/publicacao/' + id);

  return responsse.data;
};
