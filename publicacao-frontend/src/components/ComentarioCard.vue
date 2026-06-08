<template>
  <transition-group name="fade" tag="div">
    <div v-for="comment in props.comments" :key="comment.id" class="q-mt-md" v-show="showcomentario">
      <q-item>
        <q-item-section avatar>
          <q-avatar>
            <img :src="`https://i.pravatar.cc/150?img=${comment.usuario.id}`"  />
          </q-avatar>
        </q-item-section>

        <q-item-section>
          <div class="comment-box">
            <strong>{{ comment.usuario.nome }}</strong>
            <div class="text-body2">
              {{ comment.conteudo }}
            </div>
          </div>

          <div class="text-caption text-grey">
            {{ date.formatDate(comment.dataComentario, 'DD/MM/YYYY HH:mm') }}
          </div>
        </q-item-section>
      </q-item>
    </div>
  </transition-group>
</template>

<script setup lang="ts">
import type { comentarios } from './feed/types';
import { date } from 'quasar';

const props = defineProps<{ comments: comentarios[]; showcomentario: boolean }>();
</script>
<style>
/* animação suave */
.fade-enter-active,
.fade-leave-active {
  transition: all 0.50s ease;
  overflow: hidden;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
  transform: translateY(-8px);
  max-height: 0;
}

.fade-enter-to,
.fade-leave-from {
  opacity: 1;
  transform: translateY(0);
  max-height: 200px;
}
</style>
