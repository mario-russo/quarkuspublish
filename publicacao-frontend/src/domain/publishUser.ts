import type { Publicacao} from 'src/components/feed/types';
import api from './api/axiosDomain';


export const publishUser = async (id: number) => {
  const responsse = await api.get<Publicacao[]>('/publicacao/user/' + id);

  return responsse.data;
};
export const publishGetById = async (id: number) => {
  const responsse = await api.get<Publicacao>('/publicacao/' + id);

  return responsse.data;
};
