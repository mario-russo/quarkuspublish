import { defineStore } from 'pinia'

export interface UsuarioAuth{
  id :number
  email:string
  nome: string
}

export const useUsuarioStore = defineStore('usuario', {
  state: () => ({
  usuario: JSON.parse(
        localStorage.getItem('usuario') || 'null'
      ) as UsuarioAuth | null  }),

  actions: {
    setUsuario(usuario: UsuarioAuth) {
      this.usuario = usuario
      localStorage.setItem('usuario', JSON.stringify(usuario))
    },

    removeUsuario() {
      this.usuario = null
      localStorage.removeItem('usuario')
    }

  }
})
