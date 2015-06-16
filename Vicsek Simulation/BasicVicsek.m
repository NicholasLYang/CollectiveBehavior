
function [polarization] = BasicVicsek(agents, agentSize, sizeOfGraph, radius, frames, speed, fps)
agents = agents * sizeOfGraph;
% Gives a total number of agents for the for loop
numOfAgents = size(agents);
% Graphs the agents using a quiver plot (adds arrows instead of dots)
polarization = zeros(frames);
milling = zeros(frames);
cohesion = zeros(frames);
noise = zeros(frames);
for t = 1:frames


   p = [0 0];  
   m = [0 0 0];
   c = 0;
   % loops through the entire array, changing coordinates and direction
   for i = 1:numOfAgents(2)
       angle = agents(3, i);
       coords = [agents(1,i) agents(2,i)];
       direction = [cos(angle) sin(angle)];
       
       sumOfDirections = 0;
       numOfNeighbors = 0;
       % first change direction
       % need to loop through array, find which agents are "neighbors"
       for j = 1:numOfAgents(2)
           nCor = [agents(1, j) agents(2, j)];
    % r is a scalar factor of the repulsion between an agent and its neighbor.
    % e is the vector pointing from the neighbor to the agent
    % Thus, r * e is the repulsion vector. This is added to f. 
    % f is the total sum of all the repulsion vectors.
          f1 = nCor - coords;
    % Remember, f1 is a vector pointing from the agent to the neighbor

          
           distance = norm(f1);
           % If agent j is within agent i's radius
           if distance < radius
               
               nAngle = agents(3, j);
               
                   

               % Adds the direction to the total sum of directions and adds
               % 1 to the number of neighbors
               
               sumOfDirections = sumOfDirections + nAngle;
               numOfNeighbors = numOfNeighbors + 1;

               

           end
       end
      
       p = p + direction;
       m = m + cross([coords 0], [direction 0])/(norm(coords) * speed);
       c = c + exp(-norm(coords)/(2 * sizeOfGraph));
 
      % This is extrinsic noise, noise which comes from "free will"
      % agents(3,x) = mod(sumOfDirections / numOfNeighbors + rand() * pi * (1 - 2 * rand()), 2 * pi); 
      % This is intrinsic noise 
      %  angle = mod(eta * o(i) + sumOfDirections / numOfNeighbors, 2 * pi);

      % This is no noise
       agents(3, i) = mod(sumOfDirections/numOfNeighbors, 2 * pi);
       agents(1, i) = mod(speed * cos(agents(3, i)) + agents(1, i), sizeOfGraph);
       agents(2, i) = mod(speed * sin(agents(3, i)) + agents(2, i), sizeOfGraph);
     
   
 
   end
    polarization(t) = norm(p) / numOfAgents(2);
    milling(t) = norm(m)/ numOfAgents(2);    
    cohesion(t) = c / numOfAgents(2); 
    quiver(agents(1,:), agents(2, :), cos(agents(3,:)), sin(agents(3,:)), agentSize);
    axis([0,sizeOfGraph,0,sizeOfGraph]);
    pause(1/fps);
    drawnow
  end

end






