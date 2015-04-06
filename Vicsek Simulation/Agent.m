classdef Agent
    properties 
        xcor
        ycor
        velocity
        radius
        direction
    end
    methods

        function obj = Agent(radius)
            obj.radius = radius;
            obj.xcor = randi(20);
            obj.ycor = randi(20);
% the direction of the agents is determined by a random number from 0 to
% 2pi
            obj.direction = pi * (1 - 2 * rand());
          

        end
        function [] = updateDirection(j)
            totalDirection = 0;
            count = 0;
            for i = 1:size(j)
                if sqrt((j(i).xcor - obj.xcor)^2 + (j(i).ycor - obj.ycor)^2) < r
                a = a + j(i).direction; 
                count = count + 1;
                end
            end
            obj.direction = (totalDirection + obj.direction)/(count + 1); 
            
        end
        function updatePosition()
            obj.xcor = obj.xcor + obj.velocity * cos(obj.direction);
            obj.ycor = obj.ycor + obj.velocity * sin(obj.direction);
            
        end
            

    end
end
