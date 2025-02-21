<template>
  <div>
    
		<div class="card" style="margin-bottom: 5px">
			<el-input v-model="data.search.studentis" prefix-icon="Search" style="width: 240px; margin-right: 10px; margin-bottom: 3px" placeholder="请输入学号"></el-input>
			<el-input v-model="data.search.studentname" prefix-icon="Search" style="width: 240px; margin-right: 10px; margin-bottom: 3px" placeholder="请输入姓名"></el-input>
			<el-input v-model="data.search.studentName" prefix-icon="Search" style="width: 240px; margin-right: 10px; margin-bottom: 3px" placeholder="请输入手机号"></el-input>
			<el-input v-model="data.search.sendResults" prefix-icon="Search" style="width: 240px; margin-right: 10px; margin-bottom: 3px" placeholder="请输入发送结果"></el-input>
			<el-button type="info" plain @click="load">查询</el-button>
			<el-button type="warning" plain style="margin-right: 10px" @click="reset">重置</el-button>

			<el-button style="margin-right: 10px" type="warning" v-if="data.user.role === 'admin' || data.user.role === 'hkmanteacher'" @click="exp()">批量导出</el-button>
		</div>
    <div v-if="data.user.role === 'admin'" class="card" style="margin-bottom: 5px">
			<el-button v-if="data.user.role === 'admin'" type="primary" plain @click="handleAdd">新增</el-button>
			<el-button  v-if="data.user.role === 'admin'" type="danger" plain @click="delBatch">批量删除</el-button>
		</div>

    <div class="card" style="margin-bottom: 5px;">
      <el-table :data="data.tableData" strip @selection-change="handleSelectionChange">
				<el-table-column  v-if="data.user.role === 'admin'" type="selection" width="55" align="center"></el-table-column>
				<el-table-column label="学号" prop="studentis"></el-table-column>
				<el-table-column label="姓名" prop="studentname"></el-table-column>
				<el-table-column label="手机号" prop="studentName"></el-table-column>
				<el-table-column label="发送内容">
					<template v-slot="scope">
						<span v-if="!scope.row.sendContent">暂无</span>
						<el-button v-else size="small" type="primary" @click="initEditorView(scope.row.sendContent)">查看</el-button>
					</template>
				</el-table-column>
				<el-table-column label="发送结果" prop="sendResults"></el-table-column>
        <el-table-column label="操作" align="center" width="200" fixed="right">
          <template v-slot="scope">
						<el-button v-if="data.user.role === 'admin'" type="primary" plain @click="handleEdit(scope.row)">编辑</el-button>
						<el-button v-if="data.user.role === 'admin'" type="danger" plain @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="card" v-if="data.total">
      <el-pagination @current-change="load" background layout="total, prev, pager, next" :page-size="data.pageSize" v-model:current-page="data.pageNum" :total="data.total"/>
    </div>

    <el-dialog title="请填写信息" v-model="data.formVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="data.form" label-width="80px"  style="padding: 20px 30px">
				<el-form-item label="学号" prop="studentis">
					<el-select style="width: 100%" v-model="data.form.hk_studentId">
						<el-option v-for="item in data.hk_studentData" :value="item.id" :label="item.studentid" :key="item.id"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="姓名" prop="studentname">
					<el-select style="width: 100%" v-model="data.form.hk_studentId">
						<el-option v-for="item in data.hk_studentData" :value="item.id" :label="item.studentname" :key="item.id"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="手机号" prop="studentName">
					<el-select style="width: 100%" v-model="data.form.hk_studentId">
						<el-option v-for="item in data.hk_studentData" :value="item.id" :label="item.telephoneNumber" :key="item.id"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="发送内容" prop="sendContent">
					<div style="border: 1px solid #ccc; width: 100%">
						<Toolbar :editor="editorRef" :mode="mode" style="border-bottom: 1px solid #ccc" />
						<Editor v-model="data.form.sendContent" :mode="mode" :defaultConfig="editorConfig" @onCreated="handleCreated" style="height: 300px"/>
					</div>
				</el-form-item>
				<el-form-item label="发送结果" prop="sendResults">
					<el-input v-model="data.form.sendResults" placeholder="请输入发送结果"></el-input>
				</el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="data.formVisible = false">取消</el-button>
          <el-button type="primary" @click="save">保存</el-button>
        </span>
      </template>
    </el-dialog>
		<el-dialog title="信息查看" v-model="data.richTextDialogVisible" width="50%" :close-on-click-modal="false" destroy-on-close>
			<div class="w-e-text">
				<div v-html="data.send_content"></div>
			</div>
			<div slot="footer" class="dialog-footer" style="text-align: right">
				<el-button type="primary" @click="data.richTextDialogVisible = false">关 闭</el-button>
			</div>
		</el-dialog>
  </div>
</template>

<script setup>
import '@wangeditor/editor/dist/css/style.css' // 引入 css
import {onBeforeUnmount, ref, shallowRef} from "vue"
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import { reactive } from "vue"
import request from "@/utils/request";
import {ElMessage, ElMessageBox} from "element-plus";
const baseApi = 'hkDxtj'

const data = reactive({
  user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
  tableData: [],
  total: 0,
  pageNum: 1,  // 当前的页码
  pageSize: 5,  // 每页的个数
  formVisible: false,
  form: {},
  search: {},
	ids: [],
	hkStudentData: [],
	richTextDialogVisible: false,
	content: null,
})

// 初始化富文本
const editorRef = shallowRef()  // 编辑器实例，必须用 shallowRef
const mode = 'default'
const editorConfig = { MENU_CONF: {} }
// 图片上传配置
editorConfig.MENU_CONF['uploadImage'] = {
	server: 'http://localhost:9090/files/wang/upload',  // 服务端图片上传接口
	fieldName: 'file'  // 服务端图片上传接口参数
}
// 组件销毁时，也及时销毁编辑器，否则可能会造成内存泄漏
onBeforeUnmount(() => {
	const editor = editorRef.value
	if (editor == null) return
	editor.destroy()
})
// 记录 editor 实例，重要！
const handleCreated = (editor) => {
	editorRef.value = editor
}
// 查看富文本内容
const initEditorView = (content) => {
	data.content = content
	data.richTextDialogVisible = true
}

// 加载表格数据
const load = () => {

  data.search.pageNum = data.pageNum
  data.search.pageSize = data.pageSize
  request.get(baseApi + '/selectPage', {
    params: data.search
  }).then(res => {
    data.tableData = res.data?.list || []
    data.total = res.data?.total
  })
}

// 打开新增弹窗
const handleAdd = () => {
  data.form = {}
  data.formVisible = true
}

// 打开编辑弹窗
const handleEdit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}

// 新增
const add = () => {
  request.post(baseApi + '/add', data.form).then(res => {
    if (res.code === '200') {
      data.formVisible = false
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// 更新
const update = () => {
  request.put(baseApi + '/update', data.form).then(res => {
    if (res.code === '200') {
      data.formVisible = false
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// 删除
const del = (id) => {
  ElMessageBox.confirm('删除后数据无法恢复，您确定删除吗?', '删除确认', { type: 'warning' }).then(res => {
    request.delete(baseApi + '/delete/' + id).then(res => {
      if (res.code === '200') {
        ElMessage.success('删除成功')
        load()
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch(err => {
    console.error(err)
  })
}

const save = () => {
  data.form.id ? update() : add()
}

const reset = () => {
  data.search = {}
  load()
}


// 批量删除表格数据
const delBatch = () => {
	if (data.ids.length === 0) {
		ElMessage.warning('请选择数据');
		return;
	}
	ElMessageBox.confirm('删除后数据无法恢复，您确定删除吗?', '删除确认', { type: 'warning' }).then(res => {
		request.delete(baseApi + "/delete/batch", {data: data.ids}).then(res => {
			if (res.code === '200') {
				ElMessage.success('批量删除成功');
				load();
			} else {
				ElMessage.error(res.msg);
			}
		})
	}).catch(err => console.log(err))
}
const handleSelectionChange = (rows) => {
	data.ids = rows.map(v => v.id)
}
// 导出
const exp = () => {
	location.href = 'http://localhost:9090/hkDxtj/export?token=' + data.user.token
}
// 加载学生管理
const loadHkStudent = () => {
	request.get("/hkStudent/selectAll").then(res => {
		if (res.code === '200') {
			data.hkStudentData = res.data;
		} else {
			ElMessage.error(res.msg);
		}
	})
}

// 加载模块数据
load()
// 加载学生管理
loadHkStudent();

</script>
