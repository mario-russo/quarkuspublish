import api from  './axiosDomain' 

interface Login{
    email: string
    senha:string
}

interface Register{
    senha:string
    email:string
    nome:string
}

const path = "/auth"

export const AuthService = {
    async login(login: Login){
       return api.post(path+"/login",login)
    },
    async register (register: Register){
        return api.post(path + "/register", register)
    }
}