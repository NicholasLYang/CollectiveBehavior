
agents = rand(3, numOfAgents) * sizeOfGraph;
direction = zeros(10);
for i = 1:numOfAgents
    agents(3,i) = pi * (1 - 2 * rand());
end