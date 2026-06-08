<template>
  <q-page class="bg-grey-2">
    <div class="profile-container q-pa-md">
      <perfil-sobre
        :perfil="perfil"
        :nome="nome"
        :stats="stats"
        :viewEdit="false"
        @verPublicacoes="verPublicacoes"
        @verSeguidores="verSeguidores"
        @verSeguindo="verSeguindo"
      />
      <div class="text-h6 text-weight-bold q-mb-md q-mt-lg">Publicações</div>
      <q-card v-for="value in publicacoes" :key="value.publicacao_id">
        <post-card :post="value" class="q-mb-sm" @salva-like="atualizaPublicacao" @salva-post="atualizaPublicacao"> </post-card>
      </q-card>
    </div>
  </q-page>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue';
import { useQuasar } from 'quasar';
import PerfilSobre from 'src/components/perfil/PerfilSobre.vue';
import { buscaPerfilPorId, type Perfil } from 'src/domain/PerfilService';
import { buscaUsuarioPorId } from 'src/domain/usuario/UsuarioService';
import { publishUser } from 'src/domain/publishUser';
import PostCard from 'src/components/feed/PostCard.vue';
import type {Publicacao } from 'src/components/feed/types';
import { useRoute, useRouter } from 'vue-router';
import axios from 'axios';

const $q = useQuasar();
const route = useRoute();
const router = useRouter();

const perfil = reactive<Perfil>({
  titulo: '',
  sobre: '',
  id: 0,
  usuarioId: 0,
});

const stats = reactive({
  publicacoes: 0,
  seguidores: 0,
  seguindo: 0,
});
const nome = ref('');

const publicacoes = ref<Publicacao[]>([]);

const loadingPerfil = async () => {
  try {
    const id = route.params.id;
    const responsePerfil = await buscaPerfilPorId(Number(id));
    const usuario = await buscaUsuarioPorId(Number(id));
    const post = await publishUser(usuario.data.id);

    nome.value = usuario.data.nome || 'Usuário';

    perfil.usuarioId = usuario.data.id;
    publicacoes.value = post;


    stats.publicacoes = publicacoes.value.length || 0;
    stats.seguidores = 0;
    stats.seguindo = 0;

    if (responsePerfil.status !== 404) {
      perfil.titulo = responsePerfil.data.titulo || '';
      perfil.sobre = responsePerfil.data.sobre || '';
      perfil.id = responsePerfil.data.id || 0;
      perfil.usuarioId = responsePerfil.data.usuarioId || 0;
    }
  } catch (error) {
    if (axios.isAxiosError(error) && error.response?.status === 404) {
      console.log(error)
    } else {
      console.error('Erro crítico ao carregar perfil:', error);

      $q.notify({
        message: 'Erro ao carregar perfil. Tente novamente.',
        color: 'negative',
        icon: 'error',
        position: 'top',
      });
    }
  }
};

const verPublicacoes = async () => {
  if (publicacoes.value.length !== 0) {
    await router.push(`/profile/publicacao/${perfil.usuarioId}`);
  }
};

const atualizaPublicacao =  (postAtualizado: Publicacao) => {
  const findIndex =  publicacoes.value.findIndex((e) => e.publicacao_id === postAtualizado.publicacao_id);
  if (findIndex !== -1) {
   const post = publicacoes.value[findIndex];

    if (post) {
      post.likes.length = postAtualizado.likes.length;
      post.likes = postAtualizado.likes
      post.comentarios = postAtualizado.comentarios;
    }
  }

}

const verSeguidores = () => {
  $q.notify({ message: 'Feature ainda não implementada! : seguidores!', color: 'info' });
};

const verSeguindo = () => {
  $q.notify({ message: 'Feature ainda não implementada! : seguindo!', color: 'info' });
};

onMounted(async () => {
  await loadingPerfil();
});
</script>

<style scoped>
.profile-container {
  max-width: 900px;
  margin: 0 auto;
  padding-bottom: 80px;
}

.profile-card,
.publication-card {
  border-radius: 12px;
  background-color: white;
  transition: all 0.2s ease;
}

.publication-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

/* Mobile first */
@media (max-width: 600px) {
  .profile-container {
    padding: 12px;
  }
}

/* Efeito hover nas estatísticas */
.cursor-pointer:hover {
  background-color: rgba(0, 0, 0, 0.05);
  border-radius: 8px;
}
</style>
