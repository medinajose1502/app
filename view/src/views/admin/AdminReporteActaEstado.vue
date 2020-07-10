<template>
  <div>
    <NavBarA />
    <b-container>
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <b-card class="shadow-soft">
            <b-card-header header-bg-variant="primary shadow-inset">
              <h4>Reporte de actas mensuales por decanato</h4>
            </b-card-header>
            <b-card>
              <b-row>
                <b-col>
                  <b-form-group id="input-group-1" label="Estado" label-for="estatus" align="left">
                    <b-form-select id="estatus" v-model="id" :options="opc" class="mb-3" required></b-form-select>
                  </b-form-group>
                </b-col>
              </b-row>
              <b-row>
                <b-col>
                  <b-button @click="getDatos">Generar reporte</b-button>
                </b-col>
              </b-row>
              <p></p>
              <b-row>
                <b-col>
                  <b-card>
                    <b-card-body>
                      <TablaActasReporte :items="actas" />
                    </b-card-body>
                  </b-card>
                </b-col>
              </b-row>
            </b-card>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { Bar } from "vue-chartjs";
import ServiciosAPI from "@/services/ServiciosAPI.js";
import randomColor from "randomcolor";
import TablaActasReporte from "@/components/TablaActasReporte.vue";

export default {
  components: {
    TablaActasReporte
  },
  data() {
    return {
      id: "",
      opc: [],
      estatuses: [],
      actas: []
    };
  },
  async created() {
    var est = await ServiciosAPI.getEstatuses();
    {
      if (est.status == 200) this.estatuses = est.data;
      this.crearOpciones();
    }
  },
  methods: {
    async getDatos() {
      var est = await ServiciosAPI.getEstatusActaReporte(this.id);
      {
        if (est.status == 200) this.actas = est.data;
      }
    },
    crearOpciones() {
      this.estatuses.forEach(estatu => {
        var op = { value: estatu.id, text: estatu.estado };
        this.opc.push(op);
      });
    }
  }
};
</script>

<style>
</style>