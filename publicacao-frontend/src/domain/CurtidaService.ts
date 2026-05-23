import api from "./api/axiosDomain";


export interface Curtida {

  usuario_id: number;
  publicacao_id: number

}

export const curtirPublicacao = async (curtida : Curtida) => {
  const responsse = await api.post('/like',curtida);
  return responsse
};
