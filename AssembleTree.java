package com.demo.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/***
 * iterate flat list data from relation database,
 * search the iterate item in the three,
 * if found then return current tree node then add tree node
 * to tree and remove current item from list.
 * when list size is zero then the tree is builded.
 * 
 */

public class AssembleTree {

	public static void main(String args[]) {

		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		/*
		 * for(int i=0;i<5;i++){ Map<String,Object> map=new
		 * HashMap<String,Object>(); map.put("id", i-1+""); map.put("pid",
		 * i+""); map.put("name", "name"+i); mapList.add(map); }
		 */

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("id", "1");
		map1.put("pid", "-1");
		map1.put("name", "name1");
		
		mapList.add(map1);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("id", "2");
		map2.put("pid", "-1");
		map2.put("name", "name2");
		
		mapList.add(map2);

		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("id", "3");
		map3.put("pid", "-1");
		map3.put("name", "name3");
		
		mapList.add(map3);

		Map<String, Object> map21 = new HashMap<String, Object>();
		map21.put("id", "21");
		map21.put("pid", "2");
		map21.put("name", "name21");
		
		mapList.add(map21);
		
		Map<String, Object> map211 = new HashMap<String, Object>();
		map211.put("id", "211");
		map211.put("pid", "21");
		map211.put("name", "name211");
		mapList.add(map211);

		/* List<TreeNode> tree=new ArrayList<TreeNode>(); */
		/*
		 * TreeNode tree=new TreeNode(); tree.setSubTrees(assemble(mapList,-1));
		 * System.out.println(tree); dfs(tree);
		 */

		TreeNode treeNode = new TreeNode();
		treeNode.setId("-1");
		treeNode.setName("root");
		a(treeNode, mapList);
		System.out.println(treeNode);
		
		System.out.println(JSON.toJSONString(treeNode));
		dfs(treeNode);

	}

	static List<TreeNode> assemble(List<Map<String, Object>> mapList, int i) {
		List<TreeNode> subTrees = new ArrayList<TreeNode>();

		/* for(Map<String,Object> m:mapList){ */

		Iterator<Map<String, Object>> it = mapList.iterator();

		while (it.hasNext()) {
			Map<String, Object> m = it.next();
			if (m.get("pid").toString().equals("" + i)) {
				subTrees.add(new TreeNode(m.get("id").toString(), m.get("pid")
						.toString(), m.get("name").toString()));
				it.remove();
			}
		}

		while (it.hasNext()) {
			Map<String, Object> m = it.next();
			if (m.get("pid").toString().equals("" + i)) {
				it.remove();
			}
		}
		i++;
		if (i > 10) {
			return subTrees;
		}
		System.out.println(i);
		assemble(mapList, i);

		/* } */
		return subTrees;
	}

	static void dfs(TreeNode treeNode) {

		if (treeNode != null) {
			System.out.println(treeNode);
			if (treeNode.getSubTrees().size() > 0) {
				for (int i = 0; i < treeNode.getSubTrees().size(); i++) {
					dfs(treeNode.getSubTrees().get(i));
				}
			}

		}

	}
	
	static TreeNode search(TreeNode treeNode,Map<String,Object> m){
		
		if(treeNode!=null){
			
			if(treeNode.getId().toString().equals(m.get("pid").toString())){
				return treeNode;
			}else{
				TreeNode t=null;
				for(int i=0;i<treeNode.getSubTrees().size();i++){
					t=search(treeNode.getSubTrees().get(i),m);
					if(t!=null){
						return t;
					}
				}
			}
		}
		return null;
		
	}

	static void a(TreeNode treeNode, List<Map<String, Object>> mapList) {
		/*System.out.println(treeNode);*/
		if (mapList.size() == 0) {
			return;
		}

		Iterator<Map<String, Object>> it = mapList.iterator();

		TreeNode t=null;
		
		while (it.hasNext()) {
			Map<String, Object> m = it.next();
			String pid = (String) m.get("pid");
			String id = (String) m.get("id");
			String name = (String) m.get("name");

			/*TreeNode t = new TreeNode(id, pid, name);
			if (treeNode.getId().equals(pid)) {
				treeNode.getSubTrees().add(t);
			} */
			
			t=search(treeNode,m);
			if(t!=null){
				t.getSubTrees().add(new TreeNode(id,pid,name));
				it.remove();
			}
			
		}

		/*Iterator<Map<String, Object>> it1 = mapList.iterator();

		while (it1.hasNext()) {
			Map<String, Object> m = it1.next();
			String pid = (String) m.get("pid");
			String id = (String) m.get("id");
			String name = (String) m.get("name");

			if (treeNode.getId().equals(pid)) {
				it1.remove();
			}
		}*/
		
		/*for(int i=0;i<treeNode.getSubTrees().size();i++){
			TreeNode tmp=treeNode.getSubTrees().get(i);*/
			a(treeNode,mapList);
		/*}*/

	}

}

class TreeNode {

	private String id;
	private String pid;
	private String name;
	private List<TreeNode> subTrees = new ArrayList<TreeNode>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreeNode> getSubTrees() {
		return subTrees;
	}

	public void setSubTrees(List<TreeNode> subTrees) {
		this.subTrees = subTrees;
	}

	public TreeNode(String id, String pid, String name) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
	}

	public TreeNode() {
		super();
	}

	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", pid=" + pid + ", name=" + name
				+ ", subTrees=" + subTrees + "]";
	}

}
