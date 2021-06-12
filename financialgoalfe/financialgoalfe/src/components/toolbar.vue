<template>
<v-card
    class="mx-auto overflow-hidden rounded-0"
  >
    <v-app-bar
      color="dark"
      dark
    >
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

      <v-toolbar-title>FinancialGoal</v-toolbar-title>
    </v-app-bar>
    <v-dialog
        v-model="drawer"
    >
    <v-navigation-drawer
      v-model="drawer"
      absolute
      temporary
      dark
    >
      <v-list
        nav
        dense
      >
        <v-list-item-group
          v-model="group"
          active-class="blue--text text--accent-4"
        >
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Account</v-list-item-title>
          </v-list-item>
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-cog-outline</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Settings</v-list-item-title>
          </v-list-item>
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-alpha-b-circle-outline</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Bonds</v-list-item-title>
          </v-list-item>
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-alpha-e-circle-outline</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Equities</v-list-item-title>
          </v-list-item>
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-folder-edit-outline</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Folder</v-list-item-title>
          </v-list-item>
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-cash</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Movement</v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
    </v-dialog>
  </v-card>
</template>
<script>

</script>
<script>
  export default {
    data: () => ({
      drawer: false,
      group: null,
    }),
  }
</script>
