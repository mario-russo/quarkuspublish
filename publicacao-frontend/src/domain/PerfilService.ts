import api from "./api/axiosDomain";


export interface Perfil {
  id:number;
  usuarioId: number;
  sobre : string;
  titulo: string
}

export const buscaPerfilPorId = async (id : number) => {
  const responsse = await api.get('perfil/'+id);
  return responsse
};

export const perfilSetUpdate = async (perfil : Perfil)=>{
   const responsse = await api.post('perfil', perfil);
  return responsse
}
export const buscaPerfilUsuario = async ()=>{
   const responsse = await api.get('perfil');
  return responsse
}
