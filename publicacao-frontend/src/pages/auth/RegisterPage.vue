<template>
  <q-page class="row items-center justify-evenly bg-gradient-primary">
    <q-card class="register-card q-pa-lg">
      <q-card-section class="text-center">
        <q-avatar size="120px" class="q-mb-md">
          <img src="/logo.svg" alt="Logo" />
        </q-avatar>
        <div class="text-h4 text-weight-bold q-mb-sm">Criar Conta</div>
        <div class="text-grey-7">Preencha seus dados para se registrar</div>
      </q-card-section>

      <q-card-section>
        <q-form @submit.prevent="handleSubmit" class="q-gutter-y-md">
          <q-input v-model="form.name" label="Nome Completo" outlined lazy-rules :rules="nameRules">
            <template v-slot:prepend>
              <q-icon name="person" />
            </template>
          </q-input>

          <q-input
            v-model="form.email"
            label="E-mail"
            outlined
            lazy-rules
            :rules="emailRules"
            type="email"
          >
            <template v-slot:prepend>
              <q-icon name="mail" />
            </template>
          </q-input>

          <q-input
            v-model="form.password"
            label="Senha"
            outlined
            lazy-rules
            :rules="passwordRules"
            :type="showPassword ? 'text' : 'password'"
          >
            <template v-slot:prepend>
              <q-icon name="lock" />
            </template>
            <template v-slot:append>
              <q-icon
                :name="showPassword ? 'visibility_off' : 'visibility'"
                class="cursor-pointer"
                @click="showPassword = !showPassword"
              />
            </template>
          </q-input>

          <q-input
            v-model="form.confirmPassword"
            label="Confirmar Senha"
            outlined
            lazy-rules
            :rules="confirmPasswordRules"
            :type="showPassword ? 'text' : 'password'"
          >
            <template v-slot:prepend>
              <q-icon name="lock" />
            </template>
          </q-input>

          <q-checkbox
            v-model="form.terms"
            label="Eu aceito os Termos de Serviço e Política de Privacidade"
            color="primary"
            :rules="[(val: boolean) => !!val || 'Você deve aceitar os termos']"
          />

          <q-btn
            label="Cadastrar"
            type="submit"
            color="primary"
            class="full-width q-mt-md"
            size="lg"
            :loading="isLoading"
          />

          <div class="text-center q-mt-lg">
            <div class="text-caption text-grey-7 q-mb-xs">Já tem uma conta?</div>
            <router-link to="/login" class="text-primary text-weight-medium text-decoration-none">
              Fazer login
            </router-link>
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-page>
</template>

<script setup lang="ts">
import { ref, computed, reactive } from 'vue';
// import { useQuasar } from 'quasar';
// import { useRouter } from 'vue-router';
// import { useAuthStore } from 'stores/auth';

// const $q = useQuasar();
// const router = useRouter();
// const authStore = useAuthStore();

interface form {
  name: string;
  email: string;
  password: string;
  confirmPassword: string;
  terms: boolean;
}

const isLoading = ref(false);
const showPassword = ref(false);

const form = reactive({
  name: '',
  email: '',
  password: '',
  confirmPassword: '',
  terms: false,
});

// Regras de validação
const nameRules = [
  (val: string) => !!val || 'Nome é obrigatório',
  (val: string) => val.length >= 3 || 'Mínimo 3 caracteres',
];

const emailRules = [
  (val: string) => !!val || 'E-mail é obrigatório',
  (val: string) => /.+@.+\..+/.test(val) || 'E-mail inválido',
];

const passwordRules = [
  (val: string) => !!val || 'Senha é obrigatória',
  (val: string) => val.length >= 6 || 'Mínimo 6 caracteres',
  (val: string) => /[A-Z]/.test(val) || 'Pelo menos 1 letra maiúscula',
  (val: string) => /\d/.test(val) || 'Pelo menos 1 número',
];

const confirmPasswordRules = computed(() => [
  (val: string) => !!val || 'Confirme sua senha',
  (val: string) => val === form.password || 'Senhas não coincidem',
]);

const handleSubmit = async () => {
  //   try {
  //     isLoading.value = true;
  //     await authStore.register({
  //       name: form.value.name,
  //       email: form.value.email,
  //       password: form.value.password,
  //     });
  //     $q.notify({
  //       type: 'positive',
  //       message: 'Cadastro realizado com sucesso!',
  //       icon: 'check_circle',
  //     });
  //     router.push('/login');
  //   } catch (error: any) {
  //     $q.notify({
  //       type: 'negative',
  //       message: error.message || 'Falha no cadastro',
  //       icon: 'report_problem',
  //     });
  //   } finally {
  //     isLoading.value = false;
  //   }
};
</script>

<style scoped>
.register-card {
  width: 100%;
  max-width: 450px;
  border-radius: 12px;
}

.bg-gradient-primary {
  background: linear-gradient(135deg, var(--q-primary) 0%, #1976d2 100%);
}

.text-decoration-none {
  text-decoration: none;
}
</style>
