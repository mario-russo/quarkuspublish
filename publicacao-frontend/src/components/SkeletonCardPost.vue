<template>
  <div class="q-pa-md">
    <q-card flat bordered style="width: 80%; margin: 0 auto;">
       <q-item>
    <q-item-section avatar>
      <q-skeleton type="QAvatar" />
    </q-item-section>

    <q-item-section>
      <q-skeleton type="text" width="40%" />
      <q-skeleton type="text" width="25%" />
    </q-item-section>
  </q-item>

  <q-skeleton height="200px" square />

  <q-card-section>
    <q-skeleton type="text" />
    <q-skeleton type="text" width="90%" />
    <q-skeleton type="text" width="70%" />
  </q-card-section>
    </q-card>
  </div>
</template>
