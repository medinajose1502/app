<template>
  <b-card>
    <b-container fluid>
      <b-row>
        <b-col cols="8">
          <p align="left">
            <strong>Cédula:</strong>
            {{usuario.cedula}}
          </p>
          <p align="left">
            <strong>Nombres:</strong>
            {{usuario.nombres}}
          </p>
          <p align="left">
            <strong>Apellidos:</strong>
            {{usuario.apellidos}}
          </p>
        </b-col>
        <b-col>
          <router-link :to="{ name: 'AdminVerUsuario', params: {cedula: usuario.cedula} }">
            <b-button variant="warning">
              Ver Usuario
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
  props: ["usuario"]
};
</script>

<style>
</style>