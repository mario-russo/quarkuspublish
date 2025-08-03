<template>
  <q-layout view="hHh Lpr lFf">
    <!-- Barra de navegação superior (desktop) -->
    <q-header elevated class="bg-primary text-white">
      <q-toolbar>
        <q-toolbar-title>
          <q-icon name="rss_feed" size="md" class="q-mr-sm" />
          Meu Feed
        </q-toolbar-title>

        <q-space />

        <!-- Itens da direita (desktop) -->
        <div class="gt-sm">
          <q-btn flat round icon="notifications" />
          <q-btn flat round icon="mail" class="q-mx-sm" />
          <q-btn flat round icon="person" />
        </div>

        <!-- Menu mobile -->
        <q-btn flat round icon="menu" @click="toggleLeftDrawer" class="lt-md" />
      </q-toolbar>
    </q-header>

    <!-- Drawer para mobile -->
    <q-drawer v-model="leftDrawerOpen" side="left" bordered behavior="mobile" class="bg-grey-2">
      <q-list padding>
        <q-item-label header>Menu</q-item-label>

        <q-item clickable v-ripple to="/">
          <q-item-section avatar>
            <q-icon name="home" />
          </q-item-section>
          <q-item-section>Início</q-item-section>
        </q-item>

        <q-item clickable v-ripple to="/profile">
          <q-item-section avatar>
            <q-icon name="person" />
          </q-item-section>
          <q-item-section>Perfil</q-item-section>
        </q-item>

        <q-item clickable v-ripple to="/messages">
          <q-item-section avatar>
            <q-icon name="mail" />
          </q-item-section>
          <q-item-section>Mensagens</q-item-section>
        </q-item>

        <q-item clickable v-ripple to="/settings">
          <q-item-section avatar>
            <q-icon name="settings" />
          </q-item-section>
          <q-item-section>Configurações</q-item-section>
        </q-item>
      </q-list>
    </q-drawer>

    <!-- Barra de navegação inferior (mobile) -->
    <q-footer elevated class="bg-primary text-white lt-md">
      <q-tabs v-model="tab" inline-label active-color="white" indicator-color="transparent">
        <q-route-tab name="home" icon="home" label="Início" to="/" exact />
        <q-route-tab name="network" icon="people" label="Rede" to="/network" />
        <q-route-tab name="post" icon="add_circle_outline" label="Postar" to="/create-post" />
        <q-route-tab
          name="notifications"
          icon="notifications"
          label="Notificações"
          to="/notifications"
        />
        <q-route-tab name="jobs" icon="work" label="Vagas" to="/jobs" />
      </q-tabs>
    </q-footer>

    <!-- Área de conteúdo -->
    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const leftDrawerOpen = ref(false);
const tab = ref('home');

const toggleLeftDrawer = () => {
  leftDrawerOpen.value = !leftDrawerOpen.value;
};
</script>

<style scoped>
/* Estilo personalizado para os tabs */
.q-tab__icon {
  font-size: 1.5rem;
}

/* Transição suave para o drawer */
.q-drawer {
  transition: transform 0.3s ease-in-out;
}
</style>
