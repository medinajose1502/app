<template>
  <b-card>
    <b-card-header header-bg-variant="primary" header-text-variant="white">
      <h4>Lista de actas</h4>
    </b-card-header>
    <p></p>
    <b-card-body>
      <b-row>
        <b-col class="my-1">
          <b-form-group
            label="Filtro"
            label-cols-sm="3"
            label-align-sm="left"
            label-size="sm"
            label-for="filtro"
            class="mb-0"
          >
            <b-input-group size="sm">
              <b-form-input
                v-model="filtro"
                type="search"
                id="filtro"
                placeholder="Escriba algo para buscar"
              ></b-form-input>
              <b-input-group-append>
                <b-button :disabled="!filtro" @click="filtro = ''">Filtrar</b-button>
              </b-input-group-append>
            </b-input-group>
          </b-form-group>
        </b-col>
      </b-row>
      <b-row>
        <b-table
          id="tabla-actas"
          hover
          :items="act"
          :fields="fields"
          :filtro="filtro"
          :per-page="perPage"
          :current-page="currentPage"
          primary-key="id"
          @row-clicked="verActa"
        ></b-table>
      </b-row>
      <b-row>
        <b-col>
          <b-pagination
            align="right"
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
            aria-controls="tabla-actas"
          ></b-pagination>
        </b-col>
      </b-row>
    </b-card-body>
    <b-card-footer footer-bg-variant="primary">
      <b-row>
        <b-col align="right">
          <router-link :to="{ name: 'SecretarioCrearActa'}">
            <b-button variant="info">
              Crear acta
              <b-icon icon="plus-square-fill"></b-icon>
            </b-button>
          </router-link>
        </b-col>
      </b-row>
    </b-card-footer>
  </b-card>
</template>

<script>
export default {
  props: {
    items: {
      type: Array
    }
  },
  data() {
    return {
      perPage: 5,
      currentPage: 1,
      fields: [
        {
          key: "id",
          sortable: true
        },
        {
          key: "fecha",
          sortable: true
        },
        {
          key: "tipo",
          sortable: true
        }
      ],
      filtro: ""
    };
  },

  computed: {
    rows() {
      return this.act.length;
    },
    act() {
      return this.filtro
        ? this.items.filter(
            item =>
              item.fecha.includes(this.filtro) ||
              item.tipo.includes(this.filtro)
          )
        : this.items;
    }
  },
  methods: {
    verActa(item) {
      this.$router.push("/secretario/acta/ver/" + item.id);
    }
  }
};
</script>

<style>
</style>