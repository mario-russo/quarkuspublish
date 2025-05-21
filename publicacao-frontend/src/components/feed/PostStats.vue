<template>
  <q-card-section class="q-pt-none">
    <div class="flex justify-between items-center text-caption text-grey-7">
      <div>{{ likes }} curtidas • {{ comments }} comentários</div>
      <div>{{ shares }} compartilhamentos</div>
    </div>
  </q-card-section>
</template>

<script setup lang="ts">
defineProps<{
  likes: number;
  comments: number;
  shares: number;
}>();
</script>
