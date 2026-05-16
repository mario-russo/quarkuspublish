import api from "./api/axiosDomain";


interface salvaComentario {

  conteudo: string;
  dataPublicacao: Date
  publicacao_id: number

}

export const salveComents = async (comentario : salvaComentario) => {
  const responsse = await api.post('/comentarios',comentario);
  return responsse
};
