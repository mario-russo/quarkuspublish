<template>
  <q-page class="flex flex-center bg-grey-2">
    <q-card class="q-pa-md shadow-2 login-card">
      <q-card-section class="text-center">
        <q-avatar size="100px" class="q-mb-sm">
          <img src="https://cdn.quasar.dev/logo-v2/svg/logo.svg" alt="Logo" />
        </q-avatar>
        <div class="text-h4 text-weight-bold text-primary">Login</div>
      </q-card-section>

      <q-card-section>
        <q-form @submit.prevent="handleSubmit" class="q-gutter-md">
          <q-input v-model="form.email" label="Email" type="email" lazy-rules :rules="emailRules" />

          <q-input
            v-model="form.password"
            label="Senha"
            type="password"
            lazy-rules
            :rules="passwordRules"
          />

          <div class="flex justify-between items-center">
            <q-checkbox v-model="form.remember" label="Lembrar-me" />
            <router-link
              to="/forgot-password"
              class="text-caption text-primary text-decoration-none"
            >
              Esqueceu a senha?
            </router-link>
          </div>

          <div>
            <q-btn
              label="Entrar"
              type="submit"
              color="primary"
              class="full-width"
              size="lg"
              :loading="isLoading"
            />
          </div>
        </q-form>
      </q-card-section>

      <q-card-section class="text-center q-pt-none">
        <p class="text-caption">
          Não tem uma conta?
          <router-link to="/register" class="text-primary text-decoration-none">
            Cadastre-se
          </router-link>
        </p>
      </q-card-section>
    </q-card>
  </q-page>
</template>

<script setup lang="ts">
import { ref } from 'vue';

interface LoginForm {
  email: string;
  password: string;
  remember: false;
}
// Estado do componente
const isLoading = ref(false);
const form = ref<LoginForm>({
  email: '',
  password: '',
  remember: false,
});

// Regras de validação
const emailRules = [
  (val: string) => !!val || 'Email é obrigatório',
  (val: string) => /.+@.+\..+/.test(val) || 'Email inválido',
];

const passwordRules = [
  (val: string) => !!val || 'Senha é obrigatória',
  (val: string) => val.length >= 6 || 'Mínimo 6 caracteres',
];

// Função para lidar com o login
const handleSubmit = async () => {};
</script>

<style scoped>
.login-card {
  border-radius: 10px;
  width: 100%;
  max-width: 400px;
  border-left: 5px solid var(--q-primary);
}

.text-decoration-none {
  text-decoration: none;
}
</style>
