% Graph(agents, agentSize sizeOfGraph, repulsionConst, frames, speed, fps, eta, alpha, lambda, gamma)
frames = 100;
agents = AddAgents(100, 20);
[m, p, c] = Graph(agents, 0.25, 20, 0.1, frames, 1, 30, 0.1, 0.20, 0.5 , pi );



subplot(3,1,1);
plot(m);
axis([0,frames, 0, 1]);
title('Milling');
subplot(3,1,2);
plot(p)
axis([0,frames, 0, 1]);
title('Polarization');
subplot(3,1,3);
plot(c)
axis([0,frames,0,1]);
title('Cohesion');



