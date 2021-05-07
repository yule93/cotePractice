const graph = {
    A: ["B", "C"],
    B: ["A", "D"],
    C: ["A", "G", "H", "I"],
    D: ["B", "E", "F"],
    E: ["D"],
    F: ["D"],
    G: ["C"],
    H: ["C"],
    I: ["C", "J"],
    J: ["I"]
  };
  
    const bfs = (graph, startNode) => {
        let visited = []; // 탐색을 마친 노드들
        let needVisit = []; // 탐색해야할 노드들
    
        needVisit.push(startNode); // 노드 탐색 시작
    
        while (needVisit.length !== 0) { // 탐색해야할 노드가 남아있다면
        const node = needVisit.shift(); // queue이기 때문에 선입선출, shift()를 사용
        if (!visited.includes(node)) { // 해당 노드가 탐색된 적 없다면
            visited.push(node); 
            needVisit = [...needVisit, ...graph[node]];
        }
        }
        return visited;
    };
  
    const dfs = (graph, startNode) => {
        let needVisitStack = []; // 탐색을 해야 할 노드들
        let visitedQueue = []; // 탐색을 마친 노드들
    
        needVisitStack.push(startNode);
    
        // 탐색을 해야 할 노드가 남아 있다면
        while (needVisitStack.length !== 0) {
            const node = needVisitStack.pop();
            if (!visitedQueue.includes(node)) {
                visitedQueue.push(node);        // 방문했다고 true로 만듦
                for(var i = 0; i < graph[node].length; i++) {
                    visitedQueue.push(graph.node[i]);
                }
            }
        }
    
        return visitedQueue;
    };

    console.log(bfs(graph, "A"));
    console.log(dfs(graph, "A"));       // 왼쪽이랑 오른쪽이 바뀌어서 출력되는데 이걸 어떻게 해결해야할지 모르겠다....
