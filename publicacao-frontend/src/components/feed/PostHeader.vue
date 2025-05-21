<template>
  <q-card-section horizontal>
    <q-card-section class="flex items-center">
      <q-avatar size="48px" class="q-mr-sm">
        <img :src="author.avatar" />
      </q-avatar>
      <div>
        <div class="text-subtitle1">{{ author.name }}</div>
        <div class="text-caption text-grey-7">{{ author.position }} • {{ formattedDate }}</div>
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
import type { Author } from './types'; // Note o 'import type' aqui

const props = defineProps<{
  author: Author;
  date: string;
}>();

const formattedDate = computed(() => {
  return quasarDate.formatDate(props.date, 'DD/MM/YYYY HH:mm'); // Usando o alias
});
</script>
