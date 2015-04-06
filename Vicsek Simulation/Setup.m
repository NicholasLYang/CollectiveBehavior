classdef Agent
    properties 
        coords
        direction
    end
    methods

% creates a 2 by 10 array with random int values from 0 to 20. This will be
% the various agents and their x/y values
        function [coord, direction] = Setup(num)
            c = randi(20, 2, num);
% the direction of the agents is determined by a random number from 0 to
% 2pi
    d = zero(num, 1); 
    for x = 1:num
        d(x) = pi * (1 - 2 * rand());
    end
% for t = 0:100
% end
    for i = 1: 10
    fprintf('a_x is %d, a_y is %d, a_theta is %d\n', a(1,i), a(2, i), d(i));
    end
    coord = c;
    direction = d;
        end
    end
