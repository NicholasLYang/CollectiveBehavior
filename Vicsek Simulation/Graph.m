
function [agents] = Graph(agents, sizeOfGraph, repulsionConst, frames, speed, fps, eta)
% Gives a total number of agents for the for loop
numOfAgents = size(agents);
% Graphs the agents using a quiver plot (adds arrows instead of dots)
quiver(agents(1,:), agents(2,:), cos(agents(3,:)), sin(agents(3,:)));
axis([0,sizeOfGraph,0,sizeOfGraph]);
       
drawnow;
for j = 1:frames

   p = [0 0];  
   m = [0 0 0];
   % loops through the entire array, changing coordinates and direction
   for x = 1:numOfAgents(2)
       
       coords = [agents(1,x) agents(2,x)];
       direction = [cos(agents(3, x)) sin(agents(3,x))];
       
       sumOfDirections = 0;
       numOfNeighbors = 0;
       f = [0 0];
       % first change direction
       % need to loop through array, find which agents are "neighbors"
       for y = 1:numOfAgents(2)
           nCor = [agents(1, y) agents(2, y)];
    % r is a scalar factor of the repulsion between an agent and its neighbor.
    % e is the vector pointing from the neighbor to the agent
    % Thus, r * e is the repulsion vector. This is added to f. 
    % f is the total sum of all the repulsion vectors.
           
          r = (1 + exp(norm(coords - nCor)/(sizeOfGraph * 0.1) - 2))^-1;
          e = (coords - nCor)/norm(coords-nCor);  
          f = f + r * e;
           distance = norm(coords- nCor);
           if distance < sizeOfGraph/10
               
               
               f1 = nCor - coords;
               % Basic Vicsek:
               sumOfDirections = sumOfDirections + agents(3,y) * (1 + dot(direction, f1)/(norm(f1) * norm(direction))) ;
               numOfNeighbors = numOfNeighbors + 1;

               
               %{
               More complex calculations 
               velocity = speed * [cos(agents(3, x)), sin(agents(3, x))];
               omega = velocityConst * velocity * sin(agents(3, y) - agents(3,x)) + distanceConst * distance * 1 - (dot((f1-f2), f1)/(norm(f1) * norm(f2)))^2; 
               %}
         end
       end
      % Add on repulsion constant
       f = f * repulsionConst;
      % This is extrinsic noise, noise which comes from "free will"
      % agents(3,x) = mod(sumOfDirections / numOfNeighbors + rand() * pi * (1 - 2 * rand()), 2 * pi); 
      % This is intrinsic noise 
       agents(3,x) = mod(eta * rand() * 2 * pi + sumOfDirections / numOfNeighbors, 2 * pi);
      % Chate noise
      % agents(3,x) = mod( sumOfDirections / numOfNeighbors + 2 * pi * eta * real(exp(1i * rand())), 2 * pi);
       
      % This is no noise
      % agents(3,x) = mod(sumOfDirections/numOfNeighbors, 2 * pi);
      % agents(1, x) = mod(speed * cos(agents(3, x)) + agents(1, x), sizeOfGraph);
      % agents(2, x) = mod(speed * sin(agents(3, x)) + agents(2, x), sizeOfGraph);
     % More advanced with repulsion
     agents(1, x) = mod(speed * cos(agents(3,x)) + f(1) + agents(1, x), sizeOfGraph);
     agents(2, x) = mod(speed * sin(agents(3,x)) + f(2) + agents(2, x), sizeOfGraph);
     % fprintf('Agent has a repulsion of %d\n', f(1));
     % fprintf('Agent %d has xcor of %d, a ycor of %d and a direction of %d\n', x, agents(1, x), agents(2, x), agents(3,x)); 
      p = p + direction;
      m = m + cross([coords(1) coords(2) 0], [direction(1) direction(2) 0])/norm(coords);
      
   end
    quiver(agents(1,:), agents(2, :), cos(agents(3,:)), sin(agents(3,:)));
    axis([0,sizeOfGraph,0,sizeOfGraph]);
    polarization = norm(p) / numOfAgents(2) ;
    fprintf('Polarization is: %d\n', polarization);
    drawnow;
    pause(1/fps)
    
end






