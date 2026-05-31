import api from "../api/axiosDomain";


export interface Usuario {
  id: number
  nome: string
  email : string

}

export const buscaUsuarioPorId = async (id : number) => {
  const responsse = await api.get('/usuario/'+id);
  return responsse
};
