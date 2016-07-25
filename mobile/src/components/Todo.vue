<template>
    <div>
        <group title='Todo 管理'>
          <x-input :value.sync="todo_content" is-type=text v-on:keyup.enter="todoAdd" placeHolder="请输入内容并回车"></x-input>
          <div v-for="todo in todos">
            {{ todo.content }}
          </div>
        </group>
    </div>
</template>
<style>
</style>
<script>
  import Group from 'vux/dist/components/group'
  import Cell from 'vux/dist/components/cell'
  import XInput from 'vux/dist/components/x-input'
  import Toast from 'vux/dist/components/toast'
  import XButton from 'vux/dist/components/x-button'

  export default {
    components: {
      Group, Cell, XInput, Toast, XButton
    },
    data () {
      this.todoList()
      return {
        todos: [
          {
            'id': -1,
            'content': 'demo'
          }
        ],
        todo_content: ''
      }
    },
    methods: {
      todoList: function () {
        this.$http.get('/api/todo/list').then(function (resp) {
          if (resp.ok) {
            var re = resp.json()
            if (re.ok) {
              this.todos = re.data
            }
          }
        })
      },
      todoAdd: function () {
        if (!this.todo_content) {
          return
        }
        this.$http.post('/api/todo/add', {'content': this.todo_content}).then(function (resp) {
          if (resp.ok) {
            var re = resp.json()
            if (re.ok) {
              this.todo_content = ''
              this.todoList()
              return
            }
          }
        })
      }
    }
  }
</script>
