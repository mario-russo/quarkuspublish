<template>
  <q-card-section horizontal>
    <q-card-section class="flex items-center">
      <q-avatar size="48px" class="q-mr-sm">
        <img :src="avatar" />
      </q-avatar>
      <div>
        <div class="text-subtitle1 cursor-pointer" @click="irParaPerfil">{{ author.nome }}</div>
        <div class="text-caption text-grey-7">{{ formattedDate }}</div>
      </div>
    </q-card-section>

    <q-space />

    <q-card-actions vertical class="justify-around">
      <q-btn flat round icon="more_vert">
        <q-tooltip>Mais opções</q-tooltip>
      </q-btn>
    </q-card-actions>
  </q-card-section>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import { date as quasarDate } from 'quasar';
import type { usuario } from './types'; // Note o 'import type' aqui
import { useRouter } from 'vue-router';

const props = defineProps<{
  author: usuario;
  date: string;
  avatar: string;
}>();

const formattedDate = computed(() => {
  return quasarDate.formatDate(props.date, 'DD/MM/YYYY HH:mm'); // Usando o alias
});
const router = useRouter();

async function irParaPerfil() {
  // Executa qualquer lógica que você precisar aqui...

  // Navega para a rota do visitante passand o ID
  await router.push({
    name: 'perfil-visitante',
    params: { id: props.author.id },
  });
}
</script>
