function [agents] = AddAgents (numOfAgents, sizeOfGraph)
agents = rand(3, numOfAgents) * sizeOfGraph;

for i = 1:numOfAgents
   agents(3,i) = pi * (1 - 2 * rand());
end


end