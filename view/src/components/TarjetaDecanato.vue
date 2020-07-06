<template>
  <b-card>
    <b-container fluid>
      <b-row>
        <b-col cols="10">
          <p
            align="left"
          >Fecha de la sesión: {{decanato.fecha}} | Tipo de sesión: {{tipoSesion}} | Acta número: {{decanato.id}}</p>
          <p align="left">Resumen de la descripción: {{resumenDesc}}</p>
        </b-col>
        <b-col>
          <router-link :to="{ name: 'AdminVerDecanato', params: {id: decanato.id} }">
            <b-button variant="warning">
              Ver Acta
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
    tipoSesion: function() {
      if (this.decanato.tipo == "E") return "Extraordinaria";
      else return "Ordinaria";
    },

    resumenDesc: function() {
      return this.decanato.descripcion.slice(0, 30) + "...";
    }
  }
};
</script>

<style>
</style>