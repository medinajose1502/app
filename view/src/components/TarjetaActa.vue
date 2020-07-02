<template>
  <b-card>
    <b-container fluid>
      <b-row>
        <b-col cols="10">
          <p
            align="left"
          >Fecha de la sesión: {{acta.fecha}} | Tipo de sesión: {{tipoSesion}} | Acta número: {{acta.id}}</p>
          <p align="left">Resumen de la descripción: {{resumenDesc}}</p>
        </b-col>
        <b-col>
          <router-link :to="{ name: 'SecretarioVerActa', params: {id: acta.id} }">
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
  props: ["acta"],
  computed: {
    tipoSesion: function() {
      if (this.acta.tipo == "E") return "Extraordinaria";
      else return "Ordinaria";
    },

    resumenDesc: function() {
      return this.acta.descripcion.slice(0, 30) + "...";
    }
  }
};
</script>

<style>
</style>