<template>
  <b-card>
    <b-container fluid>
      <b-row>
        <b-col cols="8">
          <h4 align="left">
            <strong>Nombre:</strong>
            {{decanato.nombre}}
          </h4>
          <p align="left">
            <strong>Resumen de la descripción:</strong>
            {{resumenDesc}}
          </p>
          <p align="left">
            <strong>Ubicación:</strong>
            {{decanato.ubicacion}}
          </p>
        </b-col>
        <b-col>
          <router-link :to="{ name: 'AdminVerDecanato', params: {id: decanato.id} }">
            <b-button variant="warning">
              Ver Decanato
              <b-icon-search></b-icon-search>
            </b-button>
          </router-link>
        </b-col>
      </b-row>
    </b-container>
  </b-card>
</template>

<script>
export default {
  props: ["decanato"],
  computed: {
    resumenDesc: function() {
      return this.decanato.descripcion.slice(0, 30) + "...";
    }
  }
};
</script>

<style>
</style>