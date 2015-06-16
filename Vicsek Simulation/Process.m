% BasicVicsek(agents, agentSize, sizeOfGraph, radius, frames, speed, fps)
% Graph(agents, agentSize, sizeOfGraph, radius, repulsionConst, frames, speed, eta, alpha, lambda, gamma)
frames = 100
agents = AddAgents(200);
 
p1 = BasicNoiseVicsek(agents, 0.5, 2, 0.2, 100, 0.5, 1);


% [p1] = Graph(agents, 0.25, 1, 0.185, 0.1, frames, 0.1, 0.1, 1.71, 0.1, 1);
% plot(p1);
   
   %{
subplot(3,1,1);
plot(p1);
axis([0,frames, 0, 1]);
title('Polarization: Lambda = 0.25');
subplot(3,1,2);
plot(p2(1:100))
axis([0,frames, 0, 1]);
title('Polarization: Lambda = 0.5');
subplot(3,1,3);
plot(p3(1:100))
axis([0,frames,0,1]);
title('Polarization: Lambda = 0.75');
    %}
