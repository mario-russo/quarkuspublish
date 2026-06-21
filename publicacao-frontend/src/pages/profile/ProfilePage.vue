<template>
  <skeleton-card-post v-if="skeleton">

  </skeleton-card-post>
  <q-page v-else class="bg-grey-2">
    <div class="profile-container q-pa-md">
      <perfil-sobre
        :perfil="perfil"
        :nome="nome"
        :stats="stats"
        :view-edit="true"
        @editarPerfil="abrirModalEditar"
        @verPublicacoes="verPublicacoes"
        @verSeguidores="verSeguidores"
        @verSeguindo="verSeguindo"
      />

      <!-- ==================== TÍTULO DAS PUBLICAÇÕES ==================== -->
      <div class="text-h6 text-weight-bold q-mb-md q-mt-lg">
        Publicações
        <!-- <q-chip size="sm" color="primary" text-color="white">{{ publicacoes.length }}</q-chip> -->
      </div>

      <q-card v-for="value in publicacoes" :key="value.publicacao_id">
        <post-card :post="value" :view-actions="false" class="q-mb-md"> </post-card>
      </q-card>

      <!-- Modal de Edição -->
      <q-dialog v-model="modalEditarAberto" persistent :maximized="$q.screen.lt.md">
        <q-card style="min-width: 350px; max-width: 700px; width: 90%; height: 900px">
          <q-card-section class="bg-primary text-white">
            <div class="text-h6">Editar Perfil</div>
            <div class="text-caption">Preencha as informações abaixo</div>
          </q-card-section>

          <q-card-section class="q-pt-md">
            <!-- Campo Título -->
            <div class="q-mb-md">
              <div class="text-subtitle2 text-weight-bold q-mb-xs">Título</div>
              <q-input
                v-model="formEdicao.titulo"
                filled
                dense
                placeholder="Ex: Desenvolvedor Full Stack"
                hint="Seu título profissional ou cargo"
                :rules="[(val) => !!val || 'Campo obrigatório']"
              >
                <template v-slot:prepend>
                  <q-icon name="work" />
                </template>
              </q-input>
            </div>

            <!-- Campo Sobre -->
            <div class="q-mb-md">
              <div class="text-subtitle2 text-weight-bold q-mb-xs">Sobre</div>
              <q-input
                v-model="formEdicao.sobre"
                filled
                dense
                type="textarea"
                rows="8"
                placeholder="Conte um pouco sobre você..."
                hint="Sua biografia ou descrição pessoal"
              >
                <template v-slot:prepend>
                  <q-icon name="info" />
                </template>
              </q-input>
            </div>
          </q-card-section>

          <q-separator />

          <q-card-actions align="right" class="q-pa-md">
            <q-btn flat label="Cancelar" color="negative" v-close-popup @click="fecharModal" />
            <q-btn flat label="Salvar" color="primary" @click="salvarEdicao" />
          </q-card-actions>
        </q-card>
      </q-dialog>
    </div>
  </q-page>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue';
import { useQuasar } from 'quasar';
import PerfilSobre from 'src/components/perfil/PerfilSobre.vue';
import { buscaPerfilUsuario, type Perfil, perfilSetUpdate } from 'src/domain/PerfilService';
import { buscaUsuarioPorId } from 'src/domain/usuario/UsuarioService';
import { publishUser } from 'src/domain/publishUser';
import PostCard from 'src/components/feed/PostCard.vue';
import type { Publicacao } from 'src/components/feed/types';
import { useRouter } from 'vue-router';
import SkeletonCardPost from 'src/components/SkeletonCardPost.vue';

const $q = useQuasar();
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

const modalEditarAberto = ref(false);
const isPrimeiroCadastro = ref(false);
const skeleton = ref(false)

const formEdicao = reactive({
  titulo: '',
  sobre: '',
});

const loadingPerfil = async () => {
  try {
    const response = await buscaPerfilUsuario();

    if (!response.data) {
      isPrimeiroCadastro.value = true;
      modalEditarAberto.value = true;

      return;
    }

    if (response.data) {
      const usuario = (await buscaUsuarioPorId(response.data.usuarioId)).data;
      nome.value = usuario.nome || 'Usuário';

      const res = await publishUser(usuario.id);
      publicacoes.value = res;

      perfil.titulo = response.data.titulo || '';
      perfil.sobre = response.data.sobre || '';
      perfil.id = response.data.id || 0;
      perfil.usuarioId = response.data.usuarioId || 0;

      stats.publicacoes = publicacoes.value.length || 0;
      stats.seguidores = 0;
      stats.seguindo = 0;
    }
  } catch (error) {
    console.error('Erro ao carregar perfil:', error);

  }
};
const abrirModalEditar = () => {
  formEdicao.titulo = perfil.titulo;
  formEdicao.sobre = perfil.sobre;
  modalEditarAberto.value = true;
  isPrimeiroCadastro.value = false;
};

const fecharModal = () => {
    modalEditarAberto.value = false;
    isPrimeiroCadastro.value = false;

};

const salvarEdicao = async () => {
  try {
    if (!formEdicao.titulo.trim()) {
      $q.notify({
        message: 'Por favor, preencha o título',
        color: 'warning',
        icon: 'warning',
        position: 'top',
      });
      return;
    }

    const response = await perfilSetUpdate({
      sobre: formEdicao.sobre,
      titulo: formEdicao.titulo,
      id: 0,
      usuarioId: 0,
    });

    if (response.status === 200 || response.status === 201) {
      perfil.titulo = formEdicao.titulo;
      perfil.sobre = formEdicao.sobre;

      modalEditarAberto.value = false;

      $q.notify({
        message: isPrimeiroCadastro.value
          ? 'Perfil criado com sucesso! 🎉'
          : 'Perfil atualizado com sucesso!',
        color: 'positive',
        icon: 'check_circle',
        position: 'top',
        timeout: 2000,
      });

      await loadingPerfil();
    }
  } catch (error) {
    console.error('Erro ao salvar perfil:', error);
    $q.notify({
      message: 'Erro ao salvar perfil. Tente novamente.',
      color: 'negative',
      icon: 'error',
      position: 'top',
    });
  }
};

const verPublicacoes = async () => {
  if (!isPrimeiroCadastro.value && publicacoes.value.length > 0) {
    await router.push(`/profile/publicacao/${perfil.usuarioId}`);
  }
};

const verSeguidores = () => {
  // $q.notify({ message: 'Ver seguidores', color: 'info' });
};

const verSeguindo = () => {
  // $q.notify({ message: 'Ver seguindo', color: 'info' });
};


// Lifecycle
onMounted(async () => {
  skeleton.value = !skeleton.value
  await loadingPerfil();
  skeleton.value = !skeleton.value

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
