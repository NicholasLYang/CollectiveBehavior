
agents = AddAgents(250, 10);
[m, p] = Graph(agents, 15, 0, 100, 1, 10, 0);

subplot(2,1,1);
plot(m);
title('Milling');
subplot(2,1,2);
plot(p)
title('Polarization');