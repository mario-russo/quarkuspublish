<template>
  <!-- ==================== CARD DO PERFIL ==================== -->
  <q-card flat bordered class="profile-card q-mb-md">
    <q-card-section class="text-center">
      <!-- Foto de perfil com efeito -->
      <q-avatar size="100px" class="q-mb-md">
        <img :src="`https://i.pravatar.cc/150?img=${props.perfil.usuarioId}`" alt="foto perfil" />

        <q-badge rounded color="primary" floating class="cursor-pointer">
          <q-icon name="edit" size="12px" />
        </q-badge>
      </q-avatar>

      <!-- Nome do usuário -->
      <div class="text-h5 text-weight-bold">{{ nome }}</div>

      <!-- Título -->
      <div class="text-subtitle1 text-grey-7 q-mt-xs">{{ perfil.titulo }}</div>


    </q-card-section>

    <q-card-section>
      <!-- Descrição/Sobre -->
      <div class="text-subtitle2 text-weight-bold q-mb-xs">Sobre</div>
      <div class="text-body2 text-grey-8">
        {{ perfil.sobre }}
      </div>
    </q-card-section>

    <!-- Botões de ação -->
    <div class="row q-col-gutter-sm q-mb-md q-px-md" v-if="viewEdit">
      <div class="col-6">
        <q-btn
          outline
          rounded
          color="primary"
          label="EDITAR PERFIL"
          class="full-width text-weight-bold"
          @click="emit('editarPerfil')"
        />
      </div>
      <div class="col-6">
        <q-btn
          flat
          rounded
          color="grey-7"
          label="COMPARTILHAR"
          class="full-width text-weight-bold"
        />
      </div>
    </div>

    <q-separator />

    <!-- Estatísticas com hover effect -->
    <q-card-section>
      <div class="row text-center">
        <div class="col-4 cursor-pointer q-py-sm" @click="emit('verPublicacoes')">
          <div class="text-h6 text-weight-bold">{{ stats.publicacoes }}</div>
          <div class="text-caption text-grey-7">Publicações</div>
        </div>
        <div class="col-4 cursor-pointer q-py-sm" @click="emit('verSeguidores')">
          <div class="text-h6 text-weight-bold">{{ stats.seguidores }}</div>
          <div class="text-caption text-grey-7">Seguidores</div>
        </div>
        <div class="col-4 cursor-pointer q-py-sm" @click="emit('verSeguindo')">
          <div class="text-h6 text-weight-bold">{{ stats.seguindo }}</div>
          <div class="text-caption text-grey-7">Seguindo</div>
        </div>
      </div>
    </q-card-section>
  </q-card>
</template>

<script setup lang="ts">
import type {Perfil} from 'src/domain/PerfilService'


interface Stats {
  publicacoes: number
  seguidores: number
  seguindo: number
}


// Props do componente
const props = withDefaults( defineProps<{
  perfil: Perfil
  stats: Stats
  nome:string
  viewEdit: boolean

}>(),{viewEdit: true})

const emit = defineEmits<{
  (e: 'editarPerfil'): void
  (e: 'verPublicacoes'): void
  (e: 'verSeguidores'): void
  (e: 'verSeguindo'): void
}>()
</script>

<style scoped>
.profile-card {
  border-radius: 12px;
  background-color: white;
  transition: all 0.2s ease;
}

.profile-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.cursor-pointer:hover {
  background-color: rgba(0, 0, 0, 0.05);
  border-radius: 8px;
}
</style>
