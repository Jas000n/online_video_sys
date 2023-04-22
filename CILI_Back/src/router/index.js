import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRoutes = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  // 首页
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'CiliCili后台首页', icon: 'dashboard' }
    }]
  }]
/**
 * 动态路由
 */
export const asyncRoutes = [

  {
    path: "/producer",
    component: Layout,
    redirect: "/producer/table",
    name: "创作者管理",
    meta: { title: "创作者管理", icon: "example" },
    children: [
      {
        path: "table",
        name: "创作者列表",
        component: () => import("@/views/producer/list"),
        meta: { title: "创作者列表", icon: "table" }
      },
      {
        path: "save",
        name: "添加创作者",
        component: () => import("@/views/producer/save"),
        meta: { title: "添加创作者", icon: "tree" }
      },
      //这个路由用于在创作者list表格里点击修改按钮后数据回显到sava页面中
      {
        path: "edit/:id",
        name: "修改创作者",
        component: () => import("@/views/producer/save"),
        meta: { title: "修改创作者", nocache: true },
        hidden: true
      }
    ]
  },
  {
    path: "/classification",
    component: Layout,
    redirect: "/classification/list",
    name: "影视分类管理",
    meta: { title: "影视分类管理", icon: "example" },
    children: [
      {
        path: "list",
        name: "影视分类列表",
        component: () => import("@/views/classification/list"),
        meta: { title: "影视分类列表", icon: "table" }
      },
      {
        path: "save",
        name: "添加影视分类",
        component: () => import("@/views/classification/save"),
        meta: { title: "添加影视分类", icon: "tree" }
      }
    ]
  },
  {
    path: "/video",
    component: Layout,
    redirect: "/video/list",
    name: "影视管理",
    meta: { title: "影视管理", icon: "example" },
    children: [
      {
        path: "list",
        name: "影视列表",
        component: () => import("@/views/video/list"),
        meta: { title: "影视列表", icon: "table" }
      },
      {
        path: "info",
        name: "添加影视",
        component: () => import("@/views/video/info"),
        meta: { title: "添加影视", icon: "tree" }
      },
      {
        path: "info/:id",
        name: "VideoInfoEdit",
        component: () => import("@/views/video/info"),
        meta: { title: "编辑影视基本信息", noCache: true },
        hidden: true
      },
      {
        path: "season/:id",
        name: "VideoSeasonEdit",
        component: () => import("@/views/video/season"),
        meta: { title: "编辑季信息", noCache: true },
        hidden: true
      },
      {
        path: "publish/:id",
        name: "VideoPublishEdit",
        component: () => import("@/views/video/publish"),
        meta: { title: "发布影视", noCache: true },
        hidden: true
      }
    ]
  },
  {
    path: "/sta",
    component: Layout,
    name: "Example",
    meta: { title: "统计数据", icon: "example" },
    children: [
      {
        path: "/chart",
        name: "StatisticsDayChart",
        component: () => import("@/views/statistics/daily/chart"),
        meta: { title: "统计图表" }
      }
    ]
  },

  {
    path: '/acl',
    component: Layout,
    redirect: '/acl/user/list',
    name: '权限管理',
    meta: { title: '权限管理', icon: 'chart' },
    children: [
      {
        path: 'user/list',
        name: '用户管理',
        component: () => import('@/views/acl/user/list'),
        meta: { title: '用户管理' }
      },
      {
        path: 'role/list',
        name: '角色管理',
        component: () => import('@/views/acl/role/list'),
        meta: { title: '角色管理' }
      },
      {
        path: 'role/form',
        name: '角色添加',
        component: () => import('@/views/acl/role/form'),
        meta: { title: '角色添加' },
        hidden: true
      },
      {
        path: 'role/update/:id',
        name: '角色修改',
        component: () => import('@/views/acl/role/form'),
        meta: { title: '角色修改' },
        hidden: true
      },
      {
        path: 'role/distribution/:id',
        name: '角色权限',
        component: () => import('@/views/acl/role/roleForm'),
        meta: { title: '角色权限' },
        hidden: true
      },
      {
        path: 'menu/list',
        name: '菜单管理',
        component: () => import('@/views/acl/menu/list'),
        meta: { title: '菜单管理' }
      },
      {
        path: 'user/add',
        name: '用户添加',
        component: () => import('@/views/acl/user/form'),
        meta: { title: '用户添加' },
        hidden: true
      },
      {
        path: 'user/update/:id',
        name: '用户修改',
        component: () => import('@/views/acl/user/form'),
        meta: { title: '用户修改' },
        hidden: true
      },
      {
        path: 'user/role/:id',
        name: '用户角色',
        component: () => import('@/views/acl/user/roleForm'),
        meta: { title: '用户角色' },
        hidden: true
      }

    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// export default new Router({
//   // mode: 'history', //后端支持可开
//   scrollBehavior: () => ({ y: 0 }),
//   routes: constantRouterMap
// })

export default router
