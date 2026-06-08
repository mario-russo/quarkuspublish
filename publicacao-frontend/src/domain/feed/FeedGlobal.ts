import { type Publicacao } from "src/components/feed/types";
import api from "../api/axiosDomain";


export const buscaFeedGlobal = async (page : number, tamanho :number): Promise<Publicacao[]> => {
  const response = await api.get('feed/global/',{
     params: {
      pagina: page,
      tamanho: tamanho
    }
  });
  return response.data
};


