import api from './axiosDomain';
export interface Publish {
  usuario_id: number;
  conteudo: string;
}
export const salvePublish = async (publish: Publish) => {
  const responsse = await api.post('/publicacao', publish);

  return responsse
};
