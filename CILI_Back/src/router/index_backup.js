import Vue from "vue";
import Router from "vue-router";

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router);

/* Layout */
import Layout from "../views/layout/Layout";

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
export const constantRouterMap = [
  {
    path: "/login",
    component: () => import("@/views/login/index"),
    hidden: true
  },
  { path: "/404", component: () => import("@/views/404"), hidden: true },

  {
    path: "/",
    component: Layout,
    redirect: "/dashboard",
    name: "Dashboard",
    hidden: true,
    children: [
      {
        path: "dashboard",
        component: () => import("@/views/dashboard/index")
      }
    ]
  },
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
    path: "/form",
    component: Layout,
    children: [
      {
        path: "index",
        name: "Form",
        component: () => import("@/views/form/index"),
        meta: { title: "Form", icon: "form" }
      }
    ]
  },
  {
    path: "/nested",
    component: Layout,
    redirect: "/nested/menu1",
    name: "Nested",
    meta: {
      title: "Nested",
      icon: "nested"
    },
    children: [
      {
        path: "menu1",
        component: () => import("@/views/nested/menu1/index"), // Parent router-view
        name: "Menu1",
        meta: { title: "Menu1" },
        children: [
          {
            path: "menu1-1",
            component: () => import("@/views/nested/menu1/menu1-1"),
            name: "Menu1-1",
            meta: { title: "Menu1-1" }
          },
          {
            path: "menu1-2",
            component: () => import("@/views/nested/menu1/menu1-2"),
            name: "Menu1-2",
            meta: { title: "Menu1-2" },
            children: [
              {
                path: "menu1-2-1",
                component: () =>
                  import("@/views/nested/menu1/menu1-2/menu1-2-1"),
                name: "Menu1-2-1",
                meta: { title: "Menu1-2-1" }
              },
              {
                path: "menu1-2-2",
                component: () =>
                  import("@/views/nested/menu1/menu1-2/menu1-2-2"),
                name: "Menu1-2-2",
                meta: { title: "Menu1-2-2" }
              }
            ]
          },
          {
            path: "menu1-3",
            component: () => import("@/views/nested/menu1/menu1-3"),
            name: "Menu1-3",
            meta: { title: "Menu1-3" }
          }
        ]
      },
      {
        path: "menu2",
        component: () => import("@/views/nested/menu2/index"),
        meta: { title: "menu2" }
      }
    ]
  },

  {
    path: "external-link",
    component: Layout,
    children: [
      {
        path: "https://panjiachen.github.io/vue-element-admin-site/#/",
        meta: { title: "External Link", icon: "link" }
      }
    ]
  },

  { path: "*", redirect: "/404", hidden: true }
];

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
});
